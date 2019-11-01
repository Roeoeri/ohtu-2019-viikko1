package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void varastonTilavuusOikeinKunSyöteNegatiivinen() {
        Varasto testiVarasto = new Varasto(-1);
        assertEquals(0, testiVarasto.getTilavuus(), vertailuTarkkuus);

    }

    @Test
    public void varastonTilavuusOikeinKunSyöteNegatiivinenKahdenDoublenKonstruktorissa() {
        Varasto testiVarasto = new Varasto(-1, 10);
        assertEquals(0, testiVarasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void varastoaEiVoiAlustaaNegatiivisellaAlkusaldolla() {
        Varasto testiVarasto = new Varasto(10, -123);
        assertEquals(0, testiVarasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void varastoVoidaanAlustaaSaldollaJokaMahtuuVarastoon() {
        Varasto testiVarasto = new Varasto(10, 9);
        assertEquals(9, testiVarasto.getSaldo(), vertailuTarkkuus);

    }

    @Test
    public void varastoaEiVoiAlustaaYliTilavuuden() {
        Varasto testiVarasto = new Varasto(10, 20);
        assertEquals(10, testiVarasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void negatiivinenLisaysEiMuutaSaldoa() {
        varasto.lisaaVarastoon(-200);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void eiVoidaLisataYliTilavuuden() {
        varasto.lisaaVarastoon(200000);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenOttaminenEiMuutaSaldoa(){
        varasto.otaVarastosta(-500);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void yliSaldonOttaminenOttaaKaiken(){
        double saldo = varasto.otaVarastosta(200);
        assertEquals(0, saldo, vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    
    @Test
    public void toStringToimiiOikein(){
        String oletus = "saldo = " + 0.0 + ", vielä tilaa " + 10.0;
        assertTrue(oletus.equals(varasto.toString()));
    }
    
    

}
