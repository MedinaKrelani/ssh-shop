"use strict";
module.exports = {
  up: async (queryInterface, Sequelize) => {
    await queryInterface.createTable("addresses", {
      id: {
        allowNull: false,
        autoIncrement: true,
        primaryKey: true,
        type: Sequelize.INTEGER,
      },
      user_id: {
        type: Sequelize.INTEGER,
        allowNull: false,
      },
      street: {
        type: Sequelize.STRING,
        allowNull: false,
      },
      postal: {
        type: Sequelize.STRING,
      },
      phone_number: {
        type: Sequelize.STRING,
      },
      city_id: {
        type: Sequelize.INTEGER,
        allowNull: false,
      },
      created_at: {
        allowNull: false,
        type: Sequelize.DATE,
      },
      updated_at: {
        allowNull: false,
        type: Sequelize.DATE,
      },
    });
    await queryInterface.addConstraint("addresses", ["user_id"], {
      type: "foreign key",
      name: "user_addresses",
      references: {
        //Required field
        table: "users",
        field: "id",
      },
      onDelete: "cascade",
      onUpdate: "cascade",
    });
    await queryInterface.addConstraint("addresses", ["city_id"], {
      type: "foreign key",
      name: "city_addresses",
      references: {
        //Required field
        table: "cities",
        field: "id",
      },
      onDelete: "restrict",
      onUpdate: "cascade",
    });
    return Promise.resolve();
  },
  down: (queryInterface) => {
    return queryInterface.dropTable("addresses");
  },
};
