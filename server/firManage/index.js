const app = require("express")();
const bodyParser = require("body-parser");
const cors = require("cors");
const mongoose = require("mongoose");
const person = require("./person");

app.use(cors());
app.use(bodyParser.json());

mongoose.connect("mongodb+srv://akshay:netflixmerihai@cluster0-oxcym.mongodb.net/test?retryWrites=true&w=majority", {
  useNewUrlParser: true,
  useUnifiedTopology: true
});

app.post('/', (req, res) => {
  person.create(req.body)
  .then(p => {
    res.send(p);
  })
  .catch(() => {
    res.send({msg: "there was an error"});
  })
});

app.get('/:id', (req, res) => {
  person.findById(req.params.id)
  .then(p => {
    req.send(p);
  })
  .catch(() => {
    res.send({msg: "there was an error"});
  })
});

app.put('/:id', (req, res) => {
  person.findByIdAndUpdate(req.params.id, req.body, {new: true})
  .then(p => {
    req.send(p);
  })
  .catch(() => {
    res.send({msg: "there was an error"});
  })
})


app.listen(process.env.PORT || 8080);
