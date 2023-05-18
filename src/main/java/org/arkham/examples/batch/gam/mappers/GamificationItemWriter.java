package org.arkham.examples.batch.gam.mappers;

import org.arkham.examples.batch.gam.model.common.Gamification;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.core.io.FileSystemResource;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class GamificationItemWriter extends FlatFileItemWriter<Gamification> {

    private String fields;

    public GamificationItemWriter(String fields, String resultFolder, String dateFormat) {
        this.fields = fields;
        final SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        final String dateNow = format.format(Calendar.getInstance().getTime());
        setResource(
                new FileSystemResource(String.format(resultFolder, dateNow)));
        setLineAggregator(delimitedLineAggregator());
    }

    DelimitedLineAggregator<Gamification> delimitedLineAggregator() {
        final BeanWrapperFieldExtractor<Gamification> beanWrapperFieldExtractor = new BeanWrapperFieldExtractor<>();
        final String fieldsCsv = this.fields.replace(" ", "");
        final String[] fieldsNames = fieldsCsv.split(",");
        beanWrapperFieldExtractor.setNames(fieldsNames);

        final DelimitedLineAggregator<Gamification> delimitedLineAggregator = new DelimitedLineAggregator<>();
        delimitedLineAggregator.setDelimiter(",");
        delimitedLineAggregator.setFieldExtractor(beanWrapperFieldExtractor);
        return delimitedLineAggregator;
    }
}
