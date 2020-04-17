//users

const express = require("express");
const router = express.Router();

const controllers = require("../controllers/users");
const auth = require("../middleware/auth");

/* GET users listing. */
router.get("/", controllers.func);
/* GET user by id. */
router.get("/:user_id([0-9]+)", controllers.get);
/* Create user. */
router.post("/", controllers.create);
/* Update user. */
router.patch("/:user_id([0-9]+)", controllers.update);
/* Delete user. */
router.delete("/:user_id([0-9]+)", controllers.drop);

module.exports = router;
