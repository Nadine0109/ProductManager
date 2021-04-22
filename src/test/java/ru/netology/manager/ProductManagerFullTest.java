package ru.netology.manager;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.SmartPhone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class ProductManagerFullTest {
    @Mock
    private ProductRepository repository;
    @InjectMocks
    private ProductManager manager;
    private Product first = new Book(67, "Taiwan", 890, "Wimmelbuch");
    private Product second = new SmartPhone(90, "Samsung Y7", 50000, "China");
    private Product third = new Product(800, "Table", 60);
    private Product forth = new Book(8, "PingPong", 85932, "Utah");
    private Product fifth = new Book(76, "Potter", 54, "Rowling");
    private Product sixth = new SmartPhone(3, "Xiaomi 3", 76000, "Taiwan");
    private Product seventh = new Product(6, "Taiwan", 5400);
    private Product eighth = new Product(7, "Utah", 4);
    private Product ninth = new SmartPhone(54, "LG", 8754, "Utah");
    private Product tenth = new Book(9, "Захват", 6564, "Sigal");
    private Product eleventh = new Book(10, "Name", 999, "Nameless");

    @Test
    void shouldAddOneProduct() {
        Product[] returned = new Product[]{eleventh};
        doReturn(returned).when(repository).findAll();
        manager.add(eleventh);

        Product[] expected = new Product[]{eleventh};
        Product[] actual = manager.getAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldGetAll() {
        Product[] returned = new Product[]{first, second, third, forth, fifth, sixth, seventh, eighth, ninth, tenth, eleventh};
        doReturn(returned).when(repository).findAll();

        Product[] actual = manager.getAll();
        Product[] expected = new Product[]{first, second, third, forth, fifth, sixth, seventh, eighth, ninth, tenth, eleventh};
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchByCorrectCase() {
        Product[] returned = new Product[]{first, second, third, forth, fifth, sixth, seventh, eighth, ninth, tenth, eleventh};
        doReturn(returned).when(repository).findAll();

        String text = "Utah";
        Product[] actual = manager.searchBy(text);
        Product[] expected = new Product[]{forth, eighth, ninth};
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchByIncorrectCase() {
        Product[] returned = new Product[]{first, second, third, forth, fifth, sixth, seventh, eighth, ninth, tenth, eleventh};
        doReturn(returned).when(repository).findAll();

        String text = "taIwaN";
        Product[] actual = manager.searchBy(text);
        Product[] expected = new Product[]{first, sixth, seventh};
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldMatchNull() {
        Product product = null;
        String search = "lolipop";
        boolean actual = manager.matches(product, search);
        assertFalse(actual);
    }

    @Test
    void shouldSearchByNotExistingName() {
        Product[] returned = new Product[]{first, second, third, forth, fifth, sixth, seventh, eighth, ninth, tenth, eleventh};
        doReturn(returned).when(repository).findAll();

        String text = "Poppins";
        Product[] actual = manager.searchBy(text);
        Product[] expected = new Product[]{};
        assertArrayEquals(expected, actual);
    }
}