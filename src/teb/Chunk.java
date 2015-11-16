/*
 * $Copyright: copyright(c) 2007-2011 kuwata-lab.com all rights reserved. $
 * $License: Creative Commons Attribution (CC BY) $
 */
package teb;

import java.io.*;
import java.util.*;

import com.x5.template.*;
import teb.model.Stock;
import teb.util.DoNothingWriter;

public class Chunk extends _BenchBase {

    private Theme theme;
    public Chunk() throws Exception {
        theme = new Theme();
        theme.setTemplateFolder(new File("templates").getAbsolutePath());
        theme.setDefaultFileExtension(null);
    }

    @Override
    public void execute(Writer w0, Writer w1, int ntimes, List<Stock> items) throws Exception {
        while (--ntimes >= 0) {
            com.x5.template.Chunk chunk = theme.makeChunk("stocks.chunk.html");

            if (ntimes == 0) {
                chunk.set("items", items);
                chunk.render(w1);
                w1.close();
            }
            else {
                chunk.set("items", items);
                chunk.render(w0);
            }
        }
    }

    @Override
    public void execute(OutputStream o0, OutputStream o1, int ntimes, List<Stock> items) throws Exception {
        Writer w0 = new OutputStreamWriter(o0);
        Writer w1 = new OutputStreamWriter(o1);
        if (_BenchBase.bufferMode.get()) {
            w0 = new BufferedWriter(w0);
            w1 = new BufferedWriter(w1);
        }
        while (--ntimes >= 0) {
            com.x5.template.Chunk chunk = theme.makeChunk("stocks.chunk.html");

            if (ntimes == 0) {
                chunk.set("items", items);
                chunk.render(w1);
                w1.close();
            }
            else {
                chunk.set("items", items);
                chunk.render(w0);
            }
         }
    }

    @Override
    protected String execute(int ntimes, List<Stock> items) throws Exception {
        Writer w0 = new StringWriter();
        Writer w1 = new StringWriter();
        if (_BenchBase.bufferMode.get()) {
            w0 = new BufferedWriter(w0);
            w1 = new BufferedWriter(w1);
        }
        while (--ntimes >= 0) {
            com.x5.template.Chunk chunk = theme.makeChunk("stocks.chunk.html");

            if (ntimes == 0) {
                chunk.set("items", items);
                chunk.render(w1);
                w1.close();
            }
            else {
                chunk.set("items", items);
                chunk.render(w0);
            }
        }

        return w1.toString();
    }

    public static void main(String[] args) throws Exception {
        new Chunk().run();
    }

}
