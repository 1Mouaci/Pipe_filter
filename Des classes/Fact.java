package sample;

import javafx.util.converter.IntegerStringConverter;

import java.util.Scanner;

public class Fact extends Filter {

    Pipe _dataINPipe;
    Pipe _dataOUTPipe;

    public Fact(Pipe _dataINPipe, Pipe _dataOUTPipe) {
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
    static int factorial(int n) {
        int fact = 1;
        for (int i = 2; i <= n; i++) {
            fact = fact * i;
        }
        return fact;
    }
    @Override
    synchronized void execute() {

        System.out.println("Fact");
        // TODO Auto-generated method stub
        IntegerStringConverter converter = new IntegerStringConverter() ;
        String[] pch = new String[5];
        String p = _dataINPipe.dataOUT();

        pch = p.split("\\s+") ;
        
        int resultat  = factorial(converter.fromString(pch[0])) ;

        p = "F "+ pch[0]+" "+converter.toString(resultat) ;

       _dataOUTPipe.dataIN(p);
    }
}