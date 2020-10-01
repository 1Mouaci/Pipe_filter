package sample;

import javafx.util.converter.IntegerStringConverter;

import java.util.Scanner;

public class Produit extends Filter {

    Pipe _dataINPipe;
    Pipe _dataOUTPipe;

    public Produit(Pipe _dataINPipe, Pipe _dataOUTPipe) {
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

        System.out.println("Produit");
        // TODO Auto-generated method stub
        IntegerStringConverter converter = new IntegerStringConverter() ;
        String[] pch = new String[5];
        String p = _dataINPipe.dataOUT();

        pch = p.split("\\s+") ;

        int resultat  = converter.fromString(pch[0])*converter.fromString(pch[1]) ;

        p = "P "+ pch[0]+" "+pch[1]+" "+converter.toString(resultat) ;

       _dataOUTPipe.dataIN(p);
    }
}