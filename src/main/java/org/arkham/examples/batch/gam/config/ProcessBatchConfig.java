package org.arkham.examples.batch.gam.config;

import lombok.AllArgsConstructor;
import org.arkham.examples.batch.gam.listeners.JobCompleteListener;
import org.arkham.examples.batch.gam.mappers.GamificationHeaderWriter;
import org.arkham.examples.batch.gam.mappers.GamificationItemWriter;
import org.arkham.examples.batch.gam.model.common.Gamification;
import org.arkham.examples.batch.gam.model.document.GamificationEntity;
import org.arkham.examples.batch.gam.proccessor.GamificationProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.data.MongoItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

@Configuration
@EnableBatchProcessing
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProcessBatchConfig {

    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    @StepScope
    public MongoItemReader<GamificationEntity> itemReader(final MongoTemplate mongoTemplate) {
        final MongoItemReader<GamificationEntity> itemReader = new MongoItemReader<>();
        itemReader.setTemplate(mongoTemplate);
        itemReader.setTargetType(GamificationEntity.class);
        itemReader.setQuery(
                new Query().with(Sort.by(Sort.Direction.DESC, "_id")));
        return itemReader;
    }

    @Bean
    @StepScope
    public FlatFileItemWriter<Gamification> itemWriter(
            @Value("${batch-gam.csv.headers}") String csvFields,
            @Value("${batch-gam.result.folder}") String resultFolder, @Value("${batch-gam.date.format}") String dateFormat) {
        final GamificationHeaderWriter headerWriter = new GamificationHeaderWriter(csvFields);
        final GamificationItemWriter itemWriter = new GamificationItemWriter(csvFields, resultFolder, dateFormat);
        itemWriter.setHeaderCallback(headerWriter);
        return itemWriter;
    }

    @Bean
    public ItemProcessor<GamificationEntity, Gamification> itemProcessor() {
        return new GamificationProcessor();
    }

    @Bean
    public Job gamificationJob(JobCompleteListener listener, Step gamificationStep) {
        return this.jobBuilderFactory.get("gamificationJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(gamificationStep)
                .end().build();
    }
    @Bean
    public Step gamificationStep(
            final MongoItemReader<GamificationEntity> itemReader, final FlatFileItemWriter<Gamification> itemWriter
            ) {
        return this.stepBuilderFactory.get("gamificationStep")
                .<GamificationEntity, Gamification>chunk(50)
                .reader(itemReader)
                .processor(itemProcessor())
                .writer(itemWriter)
                .build();
    }
}
