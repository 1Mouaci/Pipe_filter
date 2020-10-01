package sample;

import javafx.util.converter.IntegerStringConverter;

import java.io.*;

public class Trace extends Filter {

    Pipe _dataINPipe;
    Pipe _dataOUTPipe;

    public Trace(Pipe _dataINPipe, Pipe _dataOUTPipe) {
        super();
        this._dataINPipe = _dataINPipe;
        this._dataOUTPipe = _dataOUTPipe;
    }



    public String getData(){
        return _dataINPipe.dataOUT();
    }

    public void sendData( String tempData){
        _dataOUTPipe.dataIN(tempData);
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        execute();
    }

    @Override
    synchronized void execute() {

        System.out.println("affiche trace");
        // TODO Auto-generated method stub
        String s = _dataINPipe.dataOUT();

        int n = 10 ;
        File file = new File("trace.txt");
        StringBuilder builder = new StringBuilder();
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile("trace.txt", "r");
            long pos = file.length() - 1;
            randomAccessFile.seek(pos);

            for (long i = pos - 1; i >= 0; i--) {
                randomAccessFile.seek(i);
                char c = (char) randomAccessFile.read();
                if (c == '\n') {
                    n--;
                    if (n == 0) {
                        break;
                    }
                }
                builder.append(c);
            }
            builder.reverse();
            System.out.println(builder.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        _dataOUTPipe.dataIN(builder.toString());
    }
}