const CustomError = require("../errors/CustomError");
const { Card, Sequelize } = require("../models/sequelize");
const Op = Sequelize.Op;

/**
 *
//  * @param {Number} card_id
//  * @returns Card
 */
const GetCard = async (card_id) => {
  try {
    const card = await Card.findByPk(card_id);
    if (!card) throw new CustomError("Not found!", {}, 401);
    return card;
  } catch (err) {
    throw err;
  }
};

const GetAllCards = async (user_id) => {
  try {
    const card = await Card.findAll({
      where: {
        user_id: user_id,
      },
    });
    if (!card) throw new CustomError("Not found!", {}, 401);
    return card;
  } catch (err) {
    throw err;
  }
};
/**
 *
 * @param {Card} data
 * @returns Card
 */
const CreateCard = async (data) => {
  let number = data.number;
  let expMonth = data.expMonth;
  let expYear = data.expYear;
  let code = data.code;

  if (number === "" || expMonth === "" || expYear === "" || code === "")
    throw new CustomError("Fill all the fields !", {}, 401);
  try {
    const card = new Card({ ...data });
    await card.validate();
    await card.save();
    return card;
  } catch (err) {
    throw err;
  }
};

/**
 *
//  * @param {Number} card_id
//  * @param {Card} data
//  * @returns Card
 */
const UpdateCard = async (card_id, user_id, data) => {
  let number = data.number;
  let expMonth = data.expMonth;
  let expYear = data.expYear;
  let code = data.code;

  if (number === "" || expMonth === "" || expYear === "" || code === "")
    throw new CustomError("Fill all the fields !", {}, 401);

  try {
    const card = await Card.findOne({
      where: {
        id: card_id,
        user_id: user_id,
      },
    });
    if (card) {
      const instance = new Card({ ...card.dataValues, ...data });
      await instance.validate();
      await card.update({
        ...instance.dataValues,
      });
      return card;
    } else {
      throw new CustomError("No combination between user and card", {}, 404);
    }
  } catch (err) {
    throw err;
  }
};

/**
 *
//  * @param {Card} card_id
//  * @returns Boolean
//  */
const DeleteCard = async (card_id) => {
  try {
    const card = await GetCard(card_id);
    await card.destroy();
    return true;
  } catch (err) {
    throw err;
  }
};

module.exports = {
  CreateCard,
  UpdateCard,
  DeleteCard,
  GetCard,
  GetAllCards,
};
