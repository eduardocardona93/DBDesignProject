'use strict';
var express = require('express');
var requests = require('./scripts/requestsHandlers');
var bodyParser = require('body-parser');
var multer = require('multer');
var upload = multer();
var app = express();
var port = process.env.PORT || 3000;
// for parsing application/json
app.use(bodyParser.json());

// for parsing application/xwww-
app.use(bodyParser.urlencoded({ extended: true }));
//form-urlencoded

// for parsing multipart/form-data
app.use(upload.array());
app.use(express.static('public'));


app.use(express.json());

var api = "/api/v1";

app.get(api + '/test', (req, res) => {
    requests.testRequest(req, res);
})
app.post(api + '/test', (req, res) => {
    requests.testRequest(req, res);
})

app.listen(port, () => {
    console.log('Server Running: ' + port)
})