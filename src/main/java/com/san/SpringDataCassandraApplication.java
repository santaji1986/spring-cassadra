package com.san;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.san.spring.data.cassandra.model.Book;
import com.san.spring.data.cassandra.repository.BookRepository;

//@SpringBootApplication
public class SpringDataCassandraApplication /* implements CommandLineRunner */ {

    @Autowired
    BookRepository customerRepository;

    public static void main(String[] args) {
        // SpringApplication.run(SpringDataCassandraApplication.class, args);
    }

    // @Override
    // public void run(String... args) throws Exception {
    // clearData();
    // saveData();
    // lookup();
    // }

    public void clearData() {
        customerRepository.deleteAll();
    }

    public void saveData() {
        System.out.println("===================Save Customers to Cassandra===================");
        final Book cust_1 = new Book(new UUID(1, 1), "Peter1", "Smith1", null);
        final Book cust_2 = new Book(new UUID(2, 1), "Peter2", "Smith2", null);
        final Book cust_3 = new Book(new UUID(3, 1), "Peter3", "Smith3", null);
        final Book cust_4 = new Book(new UUID(4, 1), "Peter4", "Smith4", null);
        final Book cust_5 = new Book(new UUID(5, 1), "Peter5", "Smith5", null);
        final Book cust_6 = new Book(new UUID(6, 1), "Peter6", "Smith6", null);

        // save customers to ElasticSearch
        customerRepository.save(cust_1);
        customerRepository.save(cust_2);
        customerRepository.save(cust_3);
        customerRepository.save(cust_4);
        customerRepository.save(cust_5);
        customerRepository.save(cust_6);
    }

    public void lookup() {
        System.out.println("===================Lookup Customers from Cassandra by Firstname===================");
        final Iterable<Book> peters = customerRepository.findByTitleAndPublisher("Peter1", "Smith1");
        System.out.println(peters);

        System.out.println("===================Lookup Customers from Cassandra by Age===================");
        final Iterable<Book> books = customerRepository.findAll();
        books.forEach(System.out::println);
    }
}
