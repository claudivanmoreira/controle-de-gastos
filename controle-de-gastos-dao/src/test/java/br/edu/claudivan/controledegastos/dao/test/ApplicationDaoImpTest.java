/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.claudivan.controledegastos.dao.test;

import br.edu.claudivan.controledegastos.dao.constants.QueryNames;
import br.edu.claudivan.controledegastos.dao.exceptions.DatabaseAccessException;
import br.edu.claudivan.controledegastos.dao.impl.CardDao;
import br.edu.claudivan.controledegastos.dao.impl.CategoryDao;
import br.edu.claudivan.controledegastos.dao.impl.DreamDao;
import br.edu.claudivan.controledegastos.dao.utils.DatabaseUtils;
import br.edu.claudivan.controledegastos.domain.Card;
import br.edu.claudivan.controledegastos.domain.Category;
import br.edu.claudivan.controledegastos.domain.Dream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hsqldb.util.DatabaseManagerSwing;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author Claudivan Moreira
 */
public class ApplicationDaoImpTest {

    private DatabaseUtils dbUtils;

    @Before
    public void setup() throws Exception {
        dbUtils = DatabaseUtils.newInstance();
        ScriptRunner runner = new ScriptRunner(dbUtils.getDataSource());
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/main/resources/create-db.sql")));
        runner.execute(reader);
    }

    @Test
    @Ignore
    public void testCardDao() {
        try {
            CardDao dao = new CardDao(dbUtils);

            System.out.println("--------- Instert ----------");
            Card c = new Card();
            c.setId(100L);
            c.setName("Nubank");
            c.setFlagOfCard("Master Card");
            c.setLimit("R$ 800,00");
            c.setInvoiceClosingDate("18");
            c.setInvoiceDueDate("25");
            Long id1 = dao.save(c);
            System.out.println("Generated card id = " + id1);

            c = new Card();
            c.setId(100L);
            c.setName("Itau");
            c.setFlagOfCard("Visa");
            c.setLimit("R$ 800,00");
            c.setInvoiceClosingDate("18");
            c.setInvoiceDueDate("25");
            Long id2 = dao.save(c);
            System.out.println("Generated card id = " + id2);

            System.out.println("--------- Find By ID ----------");
            Card card1 = dao.selectCard(id1);
            System.out.println("Saved Card 1 => " + card1);
            Card card2 = dao.selectCard(id2);
            System.out.println("Saved Card 2 => " + card2);

            System.out.println("--------- Updating Card 1 ----------");
            card1.setName("Credit Card");
            dao.update(card1);

            System.out.println("--------- Find By ID ----------");
            Card changedCard1 = dao.selectCard(card1.getId());
            System.out.println("Changed Card => " + changedCard1);

            System.out.println("--------- Selecting All Cards ----------");
            System.out.println("All Cards => " + dao.customSelectAllCards(QueryNames.SELECT_ALL_CARDS, new Object[0]));

            System.out.println("--------- Deleting Card ----------");
            dao.delete(changedCard1);

            System.out.println("--------- Selecting All Cards ----------");
            System.out.println("All Cards => " + dao.customSelectAllCards(QueryNames.SELECT_ALL_CARDS, new Object[0]));

            System.out.println("--------- Selecting Empty Result ----------");
            System.out.println("Card 1=> " + dao.selectCard(changedCard1.getId()));

        } catch (DatabaseAccessException ex) {
            Logger.getLogger(ApplicationDaoImpTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    @Ignore
    public void testCategoryDao() {
        try {
            CategoryDao dao = new CategoryDao(dbUtils);

            System.out.println("--------- Instert ----------");

            Category category1 = new Category();
            category1.setName("Alimentacao");
            category1.setType(Category.CategoryType.EXPENSE);
            Long id1 = dao.save(category1);
            System.out.println("Generated category1 id = " + id1);

            Category category2 = new Category();
            category2.setName("Transporte");
            category2.setType(Category.CategoryType.EXPENSE);
            Long id2 = dao.save(category2);
            System.out.println("Generated category2 id = " + id2);

            System.out.println("--------- Find By ID ----------");
            category1 = dao.selectCategory(id1);
            System.out.println("Saved category1 => " + category1);
            category2 = dao.selectCategory(id2);
            System.out.println("Saved category2 => " + category2);

            System.out.println("--------- Updating Category 1 ----------");
            category1.setName("Saúde");
            dao.update(category1);

            System.out.println("--------- Find By ID ----------");
            Category changedCategory1 = dao.selectCategory(category1.getId());
            System.out.println("Changed Category => " + changedCategory1);

            System.out.println("--------- Selecting All Categories ----------");
            System.out.println("All Categories => " + dao.customSelectAllCategories(QueryNames.SELECT_ALL_CATEGORIES, new Object[0]));

            System.out.println("--------- Deleting Category 1 ----------");
            dao.delete(changedCategory1);

            System.out.println("--------- Selecting All Categories ----------");
            System.out.println("All Categories => " + dao.customSelectAllCategories(QueryNames.SELECT_ALL_CATEGORIES, new Object[0]));

            System.out.println("--------- Selecting Empty Result ----------");
            System.out.println("Category 1=> " + dao.selectCategory(changedCategory1.getId()));

        } catch (DatabaseAccessException ex) {
            Logger.getLogger(ApplicationDaoImpTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testDreamDao() {
        try {
            DreamDao dao = new DreamDao(dbUtils);

            System.out.println("--------- Instert ----------");

            Dream dream1 = new Dream();
            dream1.setName("Fiat Uno");
            dream1.setAmountValue("R$ 12.000,00");
            dream1.setValueSaved("R$ 2.000,00");

            Long id1 = dao.save(dream1);
            System.out.println("Generated dream1 id = " + id1);

            Dream dream2 = new Dream();
            dream2.setName("Galaxy S7");
            dream2.setAmountValue("R$ 1.600,00");
            dream2.setValueSaved("R$ 200,00");
            Long id2 = dao.save(dream2);
            System.out.println("Generated dream2 id = " + id2);

            System.out.println("--------- Find By ID ----------");
            dream1 = dao.selectDream(id1);
            System.out.println("Saved dream1 => " + dream1);
            dream2 = dao.selectDream(id2);
            System.out.println("Saved dream2 => " + dream2);

            System.out.println("--------- Updating Dream 1 ----------");
            dream1.setName("Casa");
            dream1.setAmountValue("R$ 110.000,00");
            dao.update(dream1);

            System.out.println("--------- Find By ID ----------");
            Dream changedDream1 = dao.selectDream(dream1.getId());
            System.out.println("Changed Dream => " + changedDream1);

            System.out.println("--------- Selecting All Dreams ----------");
            System.out.println("All Dreams => " + dao.selectAllDreams());

            System.out.println("--------- Deleting Dream 1 ----------");
            dao.delete(changedDream1);

            System.out.println("--------- Selecting All Dreams ----------");
            System.out.println("All Dreams => " + dao.selectAllDreams());

            System.out.println("--------- Selecting Empty Result ----------");
            System.out.println("Dream 1=> " + dao.selectDream(dream1));

            DatabaseManagerSwing.main(new String[]{"--url", "jdbc:hsqldb:mem:controledegastos", "--user", "sa", "--password", ""});
            Thread.sleep(100000);
        } catch (Exception ex) {
            DatabaseManagerSwing.main(new String[]{"--url", "jdbc:hsqldb:mem:controledegastos", "--user", "sa", "--password", ""});
            try {
                Thread.sleep(100000);
            } catch (InterruptedException ex1) {
                Logger.getLogger(ApplicationDaoImpTest.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }

}
