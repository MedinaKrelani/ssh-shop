const { CreateUser, UpdateUser, DeleteUser, GetUser } = require("../services/users");

const func = async (req, res, next) => {
  await res.send("respond with a resource");
};

/**
 *
 * @param {Request} req
 * @param {Response} res
 */
const create = async (req, res, next) => {
  try {
    const user = await CreateUser(req.body);

    return res.status(200).json({ user });
  } catch (err) {
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
    console.log({ req });

    const user = await UpdateUser(req.params.id, req.body);

    return res.status(200).json({ user });
  } catch (err) {
    next(err);
  }
};

/**
 *
 * @param {Request} req
 * @param {Response} res
 * @param {*} next
 */
const get = async (req, res, next) => {
  try {
    return res.status(200).json({ user: await GetUser(req.params.user_id) });
  } catch (err) {
    next(err);
  }
};

module.exports = {
  get,
  create,
  update,
  func,
};