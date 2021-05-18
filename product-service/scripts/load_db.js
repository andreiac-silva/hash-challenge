use store
db.dropDatabase();

// products
db.createCollection("products");
db.getCollection('products').insert( {
    price_in_cents: 40000,
    title: "Vacina Covid Soft",
    description: "Vacina com 40% de eficácia"
} );

db.getCollection('products').insert( {
    price_in_cents: 400000,
    title: "Vacina Covid Intermediate",
    description: "Vacina com 60% de eficácia"
} );

db.getCollection('products').insert( {
    price_in_cents: 800000,
    title: "Vacina Covid Mega Power",
    description: "Vacina com 100% de eficácia"
} );

// users
db.createCollection("users");
db.getCollection('users').insert( {
    first_name: "Ragnar",
    last_name: "Lodbrok",
    date_of_birth: ISODate(1865,05,20)
} );

db.getCollection('users').insert( {
    first_name: "Bjorn",
    last_name: "Ironside",
    date_of_birth: ISODate(1865,05,21)
} );

db.getCollection('users').insert( {
    first_name: "Ivar",
    last_name: "Boneless",
    date_of_birth: ISODate(1865,05,22)
} );

db.getCollection('users').insert( {
    first_name: "Lagertha",
    lastBoneless_name: "Lodbrok",
    date_of_birth: ISODate(1865,05,23)
} );