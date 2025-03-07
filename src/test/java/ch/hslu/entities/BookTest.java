package ch.hslu.entities;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {
    @Test
    void testConstructorAndGetters() {
        Book book = new Book(
                1,
                "978-3-16-148410-0",
                "The Great Gatsby",
                "F. Scott Fitzgerald",
                "1925",
                "Scribner",
                "small.jpg",
                "medium.jpg",
                "large.jpg"
        );

        assertEquals(1, book.getId());
        assertEquals("978-3-16-148410-0", book.getIsbn());
        assertEquals("The Great Gatsby", book.getTitle());
        assertEquals("F. Scott Fitzgerald", book.getAuthor());
        assertEquals("1925", book.getYear());
        assertEquals("Scribner", book.getPublisher());
        assertEquals("small.jpg", book.getImageUrlS());
        assertEquals("medium.jpg", book.getImageUrlM());
        assertEquals("large.jpg", book.getImageUrlL());
    }

    @Test
    void testGenerateFromList() {
        List<String> bookData = List.of(
                "978-3-16-148410-0",
                "The Catcher in the Rye",
                "J.D. Salinger",
                "1951",
                "Little, Brown and Company",
                "small.jpg",
                "medium.jpg",
                "large.jpg"
        );

        Book book = Book.generateFromList(2, bookData);

        assertEquals(2, book.getId());
        assertEquals("978-3-16-148410-0", book.getIsbn());
        assertEquals("The Catcher in the Rye", book.getTitle());
        assertEquals("J.D. Salinger", book.getAuthor());
        assertEquals("1951", book.getYear());
        assertEquals("Little, Brown and Company", book.getPublisher());
        assertEquals("small.jpg", book.getImageUrlS());
        assertEquals("medium.jpg", book.getImageUrlM());
        assertEquals("large.jpg", book.getImageUrlL());
    }

    @Test
    void testEqualsAndHashCodeSameISBN() {
        Book book1 = new Book(
                1,
                "978-3-16-148410-0",
                "Book One",
                "Author One",
                "2000",
                "Publisher One",
                "small1.jpg",
                "medium1.jpg",
                "large1.jpg"
        );
        Book book2 = new Book(
                2,
                "978-3-16-148410-0",
                "Book Two",
                "Author Two",
                "2010",
                "Publisher Two",
                "small2.jpg",
                "medium2.jpg",
                "large2.jpg"
        );

        assertEquals(book1, book2);
        assertEquals(book1.hashCode(), book2.hashCode());
    }

    @Test
    void testEqualsAndHashCodeDifferentISBN() {
        Book book1 = new Book(
                1,
                "978-3-16-148410-0",
                "Book One",
                "Author One",
                "2000",
                "Publisher One",
                "small1.jpg",
                "medium1.jpg",
                "large1.jpg"
        );
        Book book2 = new Book(
                2,
                "978-0-00-000000-0",
                "Book Two",
                "Author Two",
                "2010",
                "Publisher Two",
                "small2.jpg",
                "medium2.jpg",
                "large2.jpg"
        );

        assertNotEquals(book1, book2);
        assertNotEquals(book1.hashCode(), book2.hashCode());
    }

    @Test
    void testEqualsWithDifferentObject() {
        Book book = new Book(
                1,
                "978-3-16-148410-0",
                "Book One",
                "Author One",
                "2000",
                "Publisher One",
                "small.jpg",
                "medium.jpg",
                "large.jpg"
        );

        assertNotEquals(book, "Some String");
    }

    @Test
    void testEqualsWithNull() {
        Book book = new Book(
                1,
                "978-3-16-148410-0",
                "Book One",
                "Author One",
                "2000",
                "Publisher One",
                "small.jpg",
                "medium.jpg",
                "large.jpg"
        );

        assertNotEquals(book, null);
    }

    @Test
    void testToString() {
        Book book = new Book(
                1,
                "978-3-16-148410-0",
                "Book Title",
                "Book Author",
                "2024",
                "Book Publisher",
                "small.jpg",
                "medium.jpg",
                "large.jpg"
        );

        String expected = "Book{id=1, isbn='978-3-16-148410-0', title='Book Title'}";
        assertEquals(expected, book.toString());
    }

    @Test
    void testWithEqualsVerifier() {
        EqualsVerifier.simple().forClass(Book.class)
                .withIgnoredFields("id", "title", "author", "year", "publisher", "imageUrlS", "imageUrlM", "imageUrlL")
                .verify();
    }

    @Test
    void testRemoveQuotesWithBothQuotes() {
        String input = "He said, \"It's a sunny day!\"";
        String expected = "He said, Its a sunny day!";
        assertEquals(expected, Book.removeQuotes(input));
    }

    @Test
    void testRemoveQuotesWithOnlyDoubleQuotes() {
        String input = "This \"string\" has double quotes";
        String expected = "This string has double quotes";
        assertEquals(expected, Book.removeQuotes(input));
    }

    @Test
    void testRemoveQuotesWithOnlySingleQuotes() {
        String input = "It's a test string";
        String expected = "Its a test string";
        assertEquals(expected, Book.removeQuotes(input));
    }

    @Test
    void testRemoveQuotesWithNoQuotes() {
        String input = "No quotes here!";
        String expected = "No quotes here!";
        assertEquals(expected, Book.removeQuotes(input));
    }

    @Test
    void testRemoveQuotesEmptyString() {
        String input = "";
        String expected = "";
        assertEquals(expected, Book.removeQuotes(input));
    }

    @Test
    void testRemoveQuotesNullInput() {
        String input = null;
        assertNull(Book.removeQuotes(input));
    }

    @Test
    void testRemoveQuotesOnlyQuotes() {
        String input = "\"'\"";
        String expected = "";
        assertEquals(expected, Book.removeQuotes(input));
    }

    @Test
    void testRemoveQuotesMixedQuotes() {
        String input = "\"Hello\" 'world'";
        String expected = "Hello world";
        assertEquals(expected, Book.removeQuotes(input));
    }
}