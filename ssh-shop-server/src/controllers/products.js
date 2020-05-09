const {
  CreateProduct,
  UpdateProduct,
  GetProduct,
  DeleteProduct,
} = require("../services/products");

const func = async (req, res) => {
  await res.send("create");
};

/**
 * @param {Request} req
 * @param {Response} res
 */
const create = async (req, res, next) => {
  try {
    req.body.user_id = req.user.id;
    const product = await CreateProduct(req.body);

    return res.status(200).json({ product });
  } catch (err) {
    console.log({ err });

    next(err);
  }
};

/**
 *
 * @param {Request} req
 * @param {Response} res
 */

const update = async (req, res, next) => {
  try {
    const product = await UpdateProduct(
      req.params.product_id,
      req.user.id,
      req.body
    );
    return res.status(200).json({ product });
  } catch (err) {
    next(err);
  }
};

const get = async (req, res, next) => {
  try {
    return res
      .status(200)
      .json({ user: await GetProduct(req.params.product_id) });
  } catch (err) {
    next(err);
  }
};

/**
 *
 * @param {Request} req
 * @param {Response} res
 */
const drop = async (req, res, next) => {
  try {
    const response = await DeleteProduct(req.params.product_id);
    return res.status(200).json(response);
  } catch (err) {
    next(err);
  }
};

module.exports = {
  get,
  create,
  func,
  update,
  drop,
};