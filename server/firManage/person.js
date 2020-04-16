const mongoose = require("mongoose");

const personSchema = new mongoose.Schema({
  firstName: String,
  middleName: String,
  lastName: String,
  identificationDocumentType: String,
  applicantType: String,
  houseNo: String,
  streetName: String,
  area: String,
  city: String,
  block: String,
  gender: String,
  mobileNumber: String,
  country: String,
  state: String,
  district: String,
  policeStation: String,
  pincode: String
});

module.exports = mongoose.model("person", personSchema);
