package com.hash.challenge.repository;

import com.hash.challenge.config.DatabaseConfig;
import com.hash.challenge.domain.Product;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ProductRepository {

    @Inject
    DatabaseConfig databaseConfig;

    @Inject
    MongoClient mongoClient;

    public List<Product> findAll() {
        List<Product> list = new ArrayList<>();
        MongoCursor<Document> cursor = getCollection().find().iterator();

        try {
            while (cursor.hasNext()) {
                var document = cursor.next();
                list.add(fromDocument(document));
            }
        } finally {
            cursor.close();
        }
        return list;
    }

    public void save(final Product product) {
        getCollection().insertOne(toDocument(product));
    }

    private MongoCollection getCollection() {
        return mongoClient.getDatabase(databaseConfig.name()).getCollection(databaseConfig.productCollection());
    }

    private Product fromDocument(final Document document) {
        var product = new Product();

        System.out.println("Object id= "+ document.getObjectId("_id"));
        System.out.println("Object id= "+ document.getObjectId("_id").toString());

        product.setId(document.getObjectId("_id").toString());
        product.setPriceInCents(document.getInteger("price_in_cents"));
        product.setTitle(document.getString("title"));
        product.setDescription(document.getString("description"));
        return product;
    }

    private Document toDocument(final Product product) {
        return new Document()
                .append("price_in_cents", product.getPriceInCents())
                .append("title", product.getTitle())
                .append("description", product.getDescription());
    }
}
