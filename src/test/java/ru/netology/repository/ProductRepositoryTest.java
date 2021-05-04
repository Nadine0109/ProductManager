package ru.netology.repository;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.SmartPhone;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {
    private ProductRepository repository = new ProductRepository();
    private Book oneBook = new Book();
    private Product first = new Book(67, "Hurt", 890, "Wimmelbuch");
    private Product second = new SmartPhone(90, "Samsung Y7", 50000, "China");
    private Product third = new Product(800, "Lozhka", 60);
    private Product forth = new Book(8, "PingPong", 85932, "Utah");
    private Product fifth = new Book(76, "Potter", 54, "Rowling");

    @Test
    public void shouldSaveOneItem() {
        repository.save(oneBook);

        Product[] expected = new Product[]{oneBook};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindAll() {
        repository.save(first);
        repository.save(second);
        repository.save(third);
        repository.save(forth);
        repository.save(fifth);
        repository.findAll();
        Product[] expected = new Product[]{first, second, third, forth, fifth};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindById() {
        repository.save(first);
        repository.save(second);
        repository.save(third);
        repository.save(forth);
        repository.save(fifth);
        int id = 8;
        Product expected = forth;
        Product actual = repository.findById(id);
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindByIdNonExisting() {
        repository.save(first);
        repository.save(second);
        repository.save(third);
        repository.save(forth);
        int id = 76;
        Product expected = null;
        Product actual = repository.findById(id);
        assertEquals(expected, actual);

    }

    @Test
    void shouldRemoveById() {
        repository.save(fifth);
        repository.save(forth);
        repository.save(third);
        int id = 76;
        repository.findById(id);
        Product[] expected = new Product[]{forth, third};
        Product[] actual = repository.removeById(id);
        assertArrayEquals(expected, actual);
    }


    @Test
    void shouldTestThrowingException() {
        repository.save(fifth);
        int id = 7;
        assertThrows(NotFoundException.class, () -> {
            repository.removeById(id);
        });
    }
}