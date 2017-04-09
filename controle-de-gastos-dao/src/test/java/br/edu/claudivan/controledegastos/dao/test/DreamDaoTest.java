/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.claudivan.controledegastos.dao.test;

import br.edu.claudivan.controledegastos.dao.exceptions.DatabaseAccessException;
import br.edu.claudivan.controledegastos.dao.exceptions.EmptyResultException;
import br.edu.claudivan.controledegastos.dao.impl.DreamDao;
import br.edu.claudivan.controledegastos.dao.test.utils.TestUtils;
import br.edu.claudivan.controledegastos.dao.utils.DatabaseUtils;
import br.edu.claudivan.controledegastos.domain.Dream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Claudivan Moreira
 */
public class DreamDaoTest {

    private static DreamDao dao;
    private static DatabaseUtils dbUtils;

    @BeforeClass
    public static void setup() throws Exception {
        dbUtils = TestUtils.prepareBD();
        dao = new DreamDao(dbUtils);
    }

    @After
    public void cleanDatabaseBetweenTests() throws Exception {
        TestUtils.cleanDatabaseBetweenTests(dbUtils);
    }

    @Test
    public void testInsert() throws Exception {
        try {
            Dream dream = new Dream();
            dream.setName("Fiat Uno");
            dream.setAmountValue("R$ 12.000,00");
            dream.setValueSaved("R$ 2.000,00");
            dao.save(dream);//ID 1

            Assert.assertEquals(1L, dream.getId().longValue());

            dream = new Dream();
            dream.setName("Galaxy S7");
            dream.setAmountValue("R$ 1.600,00");
            dream.setValueSaved("R$ 200,00");
            dao.save(dream);//ID 2

            Assert.assertEquals(2L, dream.getId().longValue());
            Assert.assertEquals(2, dao.selectAllDreams().size());

        } catch (DatabaseAccessException ex) {
            Logger.getLogger(CardDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testUpdate() {
        try {
            Dream dream = new Dream();
            dream.setName("Fiat Uno");
            dream.setAmountValue("R$ 12.000,00");
            dream.setValueSaved("R$ 2.000,00");
            dao.save(dream);//ID 1

            Dream savedDream = dao.selectDream(dream.getId());
            Assert.assertEquals("Fiat Uno", savedDream.getName());

            savedDream.setName("Galaxy S7");
            savedDream.setAmountValue("R$ 1.300,00");
            dao.update(savedDream);

            Assert.assertEquals("Galaxy S7", dao.selectDream(savedDream.getId()).getName());

        } catch (DatabaseAccessException ex) {
            Logger.getLogger(CardDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testDelete() {
        try {
            Dream dream = new Dream();
            dream.setName("Casa");
            dream.setAmountValue("R$ 12.000,00");
            dream.setValueSaved("R$ 2.000,00");
            dao.save(dream);//ID 1

            dream = new Dream();
            dream.setName("Xbox One");
            dream.setAmountValue("R$ 1.600,00");
            dream.setValueSaved("R$ 200,00");
            dao.save(dream);//ID 2

            Assert.assertEquals(2, dao.selectAllDreams().size());
            System.out.println(dream);
            dao.delete(dream);
            Assert.assertEquals(1, dao.selectAllDreams().size());

        } catch (DatabaseAccessException ex) {
            Logger.getLogger(CardDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test(expected = EmptyResultException.class)
    public void testEmptySelect() {
        try {
            Dream dream = new Dream();
            dream.setName("Fiat Uno");
            dream.setAmountValue("R$ 12.000,00");
            dream.setValueSaved("R$ 2.000,00");
            dao.save(dream);//ID 1

            dream = new Dream();
            dream.setName("Galaxy S7");
            dream.setAmountValue("R$ 1.600,00");
            dream.setValueSaved("R$ 200,00");
            dao.save(dream);//ID 2

            Assert.assertEquals(2, dao.selectAllDreams().size());
            //Delete ID 2
            dao.delete(dream);

            Assert.assertEquals(1, dao.selectAllDreams().size());
            //Sem Resultado, lanca EmptyResultException pois ID 2 nao existe
            dao.selectDream(dream.getId());

        } catch (DatabaseAccessException ex) {
            Logger.getLogger(CardDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
