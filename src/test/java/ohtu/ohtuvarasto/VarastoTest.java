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
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
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
    public void konstr() {
        varasto = new Varasto(-1);
        varasto = new Varasto(0);
        varasto = new Varasto(1,1);
        varasto = new Varasto(1,2);
        varasto = new Varasto(-1,2);
        varasto = new Varasto(-1,-1);
        varasto.toString();
    }
    
    @Test
    public void eiLiikaaTavaraa() {
        varasto.lisaaVarastoon(11);
        
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void eiLiikaaOttoa() {
        varasto.lisaaVarastoon(3);
        
        varasto.otaVarastosta(5);
        
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void josSaldoIsompiKuinTilavuusSaldoksiLaitetaanTilavuus() {
        double herp = varasto.getSaldo()+10;
        varasto.lisaaVarastoon(herp);
        
        assertEquals(varasto.getSaldo(), varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void josOtetaanLiikaa() {
        varasto.lisaaVarastoon(10);
        double herp = varasto.otaVarastosta(15);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
        assertEquals(10, herp, vertailuTarkkuus);
    }
    
    @Test
    public void eiNegatiivistaLisaysta() {
        varasto.lisaaVarastoon(-5);
        
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void saldoOikeinKunOtetaanLiikaa() {
        varasto.otaVarastosta(varasto.getSaldo()+2);
        
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void lisataanVarastoonSopivasti() {
        varasto.lisaaVarastoon(varasto.paljonkoMahtuu()-5);
        
        assertEquals(5, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void oikeaSaldoKunLisataanLiikaa() {
        varasto.lisaaVarastoon(varasto.paljonkoMahtuu()+30);
        
        assertEquals(varasto.getTilavuus(), varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void otetaanNegatiivinenMaara() { // RIKOTTU
        varasto.otaVarastosta(-1);
        
        assertEquals(2, varasto.getSaldo(), vertailuTarkkuus);
    }
}