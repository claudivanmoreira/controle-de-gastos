/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.claudivan.controledegastos.dao.test;

import br.edu.claudivan.controledegastos.dao.exceptions.DatabaseAccessException;
import br.edu.claudivan.controledegastos.dao.exceptions.EmptyResultException;
import br.edu.claudivan.controledegastos.dao.impl.CategoryDao;
import br.edu.claudivan.controledegastos.dao.test.utils.TestUtils;
import br.edu.claudivan.controledegastos.dao.utils.DatabaseUtils;
import br.edu.claudivan.controledegastos.domain.Category;
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
public class CategoryDaoTest {

    private static CategoryDao dao;
    private static DatabaseUtils dbUtils;

    @BeforeClass
    public static void setup() throws Exception {
        dbUtils = TestUtils.prepareBD();
        dao = new CategoryDao(dbUtils);
    }

    @After
    public void cleanDatabaseBetweenTests() throws Exception {
        TestUtils.cleanDatabaseBetweenTests(dbUtils);
    }

    @Test
    public void testInsert() throws Exception {
        try {
            Category category = new Category();
            category.setName("Alimentacao");
            category.setType(Category.CategoryType.EXPENSE);
            dao.save(category);//ID 1

            Assert.assertEquals(1L, category.getId().longValue());

            category = new Category();
            category.setName("Transporte");
            category.setType(Category.CategoryType.EXPENSE);
            dao.save(category);//ID 2            

            Assert.assertEquals(2L, category.getId().longValue());
            Assert.assertEquals(2, dao.selectAllCategories().size());

        } catch (DatabaseAccessException ex) {
            Logger.getLogger(CategoryDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testUpdate() {
        try {
            Category category = new Category();
            category.setName("Alimentacao");
            category.setType(Category.CategoryType.EXPENSE);
            dao.save(category);//ID 1

            Category savedCategory = dao.selectCategory(category.getId());
            Assert.assertEquals("Alimentacao", savedCategory.getName());

            savedCategory.setName("Saúde");
            dao.update(savedCategory);

            Assert.assertEquals("Saúde", dao.selectCategory(savedCategory.getId()).getName());

        } catch (DatabaseAccessException ex) {
            Logger.getLogger(CategoryDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testDelete() {
        try {
            Category category = new Category();
            category.setName("Alimentacao");
            category.setType(Category.CategoryType.EXPENSE);
            dao.save(category);//ID 1

            category = new Category();
            category.setName("Transporte");
            category.setType(Category.CategoryType.EXPENSE);
            dao.save(category);//ID 2  

            Assert.assertEquals(2, dao.selectAllCategories().size());

            dao.delete(category);
            Assert.assertEquals(1, dao.selectAllCategories().size());

        } catch (DatabaseAccessException ex) {
            Logger.getLogger(CategoryDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test(expected = EmptyResultException.class)
    public void testEmptySelect() {
        try {
            Category category = new Category();
            category.setName("Alimentacao");
            category.setType(Category.CategoryType.EXPENSE);
            dao.save(category);//ID 1

            category = new Category();
            category.setName("Transporte");
            category.setType(Category.CategoryType.EXPENSE);
            dao.save(category);//ID 2  

            Assert.assertEquals(2, dao.selectAllCategories().size());
            //Delete ID 2
            dao.delete(category);

            Assert.assertEquals(1, dao.selectAllCategories().size());
            //Sem Resultado, lanca EmptyResultException pois ID 2 nao existe
            dao.selectCategory(category.getId());

        } catch (DatabaseAccessException ex) {
            Logger.getLogger(CategoryDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
