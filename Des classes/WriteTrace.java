package sample;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class WriteTrace  extends Filter {

    public WriteTrace(Pipe _dataINPipe, Pipe _dataOUTPipe) {
        super();
        this._dataINPipe = _dataINPipe;
        this._dataOUTPipe = _dataOUTPipe;
    }

    Pipe _dataINPipe;
    Pipe _dataOUTPipe;

    public String getData() {
        return _dataINPipe.dataOUT();
    }

    public void sendData(String tempData) {
        _dataOUTPipe.dataIN(tempData);
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        execute();
    }

    @Override
    synchronized void execute() {
        // TODO Auto-generated method stub
        System.out.println("trace");
        String nomFichier = "trace.txt";
        String[] pch = new String[5];

        String s = _dataINPipe.dataOUT();

        pch = s.split("\\s+") ;

        File ff = new File(nomFichier); // définir l'arborescence
        try {
            FileWriter ffw = new FileWriter(ff, true);
            ffw.write(s+"\n");
            ffw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        _dataOUTPipe.dataIN(pch[pch.length-1]);

    }

}