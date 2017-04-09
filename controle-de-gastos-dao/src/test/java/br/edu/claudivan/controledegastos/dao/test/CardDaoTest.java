/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.claudivan.controledegastos.dao.test;

import br.edu.claudivan.controledegastos.dao.exceptions.DatabaseAccessException;
import br.edu.claudivan.controledegastos.dao.exceptions.EmptyResultException;
import br.edu.claudivan.controledegastos.dao.impl.CardDao;
import br.edu.claudivan.controledegastos.dao.test.utils.TestUtils;
import br.edu.claudivan.controledegastos.dao.utils.DatabaseUtils;
import br.edu.claudivan.controledegastos.domain.Card;
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
public class CardDaoTest {

    private static CardDao dao;
    private static DatabaseUtils dbUtils;

    @BeforeClass
    public static void setup() throws Exception {
        dbUtils = TestUtils.prepareBD();
        dao = new CardDao(dbUtils);
    }
    
    @After
    public void cleanDatabaseBetweenTests() throws Exception {
        TestUtils.cleanDatabaseBetweenTests(dbUtils);
    }

    @Test
    public void testInsert() throws Exception {
        try {
            Card card = new Card();
            card.setId(100L);
            card.setName("Nubank");
            card.setFlagOfCard("Master Card");
            card.setLimit("R$ 800,00");
            card.setInvoiceClosingDate("18");
            card.setInvoiceDueDate("25");
            dao.save(card);//ID 1

            Assert.assertEquals(1L, card.getId().longValue());
            
            card = new Card();
            card.setId(100L);
            card.setName("Itau");
            card.setFlagOfCard("Visa");
            card.setLimit("R$ 8100,00");
            card.setInvoiceClosingDate("10");
            card.setInvoiceDueDate("25");
            dao.save(card);//ID 2
      

            Assert.assertEquals(2L, card.getId().longValue());
            Assert.assertEquals(2, dao.selectAllCards().size());

        } catch (DatabaseAccessException ex) {
            Logger.getLogger(CardDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testUpdate() {
        try {
            Card card = new Card();
            card.setId(100L);
            card.setName("Nubank");
            card.setFlagOfCard("Master Card");
            card.setLimit("R$ 800,00");
            card.setInvoiceClosingDate("18");
            card.setInvoiceDueDate("25");
            dao.save(card);//ID 1

            Card savedCard = dao.selectCard(card.getId());
            Assert.assertEquals("Nubank", savedCard.getName());

            savedCard.setName("Credit Card");
            dao.update(savedCard);

            Assert.assertEquals("Credit Card", dao.selectCard(savedCard.getId()).getName());

        } catch (DatabaseAccessException ex) {
            Logger.getLogger(CardDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testDelete() {
        try {
            Card card = new Card();
            card.setId(100L);
            card.setName("Nubank");
            card.setFlagOfCard("Master Card");
            card.setLimit("R$ 800,00");
            card.setInvoiceClosingDate("18");
            card.setInvoiceDueDate("25");
            dao.save(card);//ID 1
            
            card = new Card();
            card.setId(100L);
            card.setName("Itau");
            card.setFlagOfCard("Visa");
            card.setLimit("R$ 8100,00");
            card.setInvoiceClosingDate("10");
            card.setInvoiceDueDate("25");
            dao.save(card);//ID 2 

            Assert.assertEquals(2, dao.selectAllCards().size());

            dao.delete(card);
            Assert.assertEquals(1, dao.selectAllCards().size());

        } catch (DatabaseAccessException ex) {
            Logger.getLogger(CardDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test(expected = EmptyResultException.class)
    public void testEmptySelect() {
        try {
            Card card = new Card();
            card.setId(100L);
            card.setName("Nubank");
            card.setFlagOfCard("Master Card");
            card.setLimit("R$ 800,00");
            card.setInvoiceClosingDate("18");
            card.setInvoiceDueDate("25");
            dao.save(card);//ID 1
            
            card = new Card();
            card.setId(100L);
            card.setName("Itau");
            card.setFlagOfCard("Visa");
            card.setLimit("R$ 8100,00");
            card.setInvoiceClosingDate("10");
            card.setInvoiceDueDate("25");
            dao.save(card);//ID 2 

            Assert.assertEquals(2, dao.selectAllCards().size());
            //Delete ID 2
            dao.delete(card);

            Assert.assertEquals(1, dao.selectAllCards().size());
            //Sem Resultado, lanca EmptyResultException pois ID 2 nao existe
            dao.selectCard(card.getId());

        } catch (DatabaseAccessException ex) {
            Logger.getLogger(CardDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
