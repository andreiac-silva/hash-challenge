print('Starting mongo initilization...');

db = db.getSiblingDB('store')

print('Creating products and users collections...');
db.createCollection("products");
db.createCollection("users");

print('Inserting some products...');
db.getCollection('products').insert( {
    price_in_cents: 10000,
    title: "Vacina Covid",
    description: "Vacina com 70% de eficácia."
} );

db.getCollection('products').insert( {
    price_in_cents: 40000,
    title: "Vacina Covid Power",
    description: "Vacina com 80% de eficácia"
} );

db.getCollection('products').insert( {
    price_in_cents: 80000,
    title: "Vacina Covid Mega Power",
    description: "Vacina com 100% de eficácia"
} );

print('Inserting some users...');
db.getCollection('users').insert( {
    first_name: "Ragnar",
    last_name: "Lodbrok",
    date_of_birth: ISODate("1970-05-19T00:00:00.000")
} );

db.getCollection('users').insert( {
    first_name: "Lagertha",
    lastBoneless_name: "Lodbrok",
    date_of_birth: ISODate("1975-05-20T00:00:00.000")
} );

db.getCollection('users').insert( {
    first_name: "Bjorn",
    last_name: "Ironside",
    date_of_birth: ISODate("2000-05-21T00:00:00.000")
} );

db.getCollection('users').insert( {
    first_name: "Ivar",
    last_name: "Boneless",
    date_of_birth: ISODate("2005-05-22T00:00:00.000")
} );

print('Database initilized successfully!');
