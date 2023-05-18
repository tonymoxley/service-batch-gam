package org.arkham.examples.batch.gam.mappers;

import org.springframework.batch.item.file.FlatFileHeaderCallback;

import java.io.IOException;
import java.io.Writer;

public class GamificationHeaderWriter implements FlatFileHeaderCallback {

    private String headerName;

    public GamificationHeaderWriter(String headerName) {
        this.headerName = headerName;
    }

    @Override
    public void writeHeader(Writer writer) throws IOException {
        writer.write(this.headerName);
    }
}
