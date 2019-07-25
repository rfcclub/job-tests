// Main js file for server-side
// normal constants
const DEFAULT_PORT = 8080;
const DEFAULT_HOST = 'localhost';

// express related
const express = require('express');
const bodyParser = require('body-parser');
const path = require('path');

// express is the routing engine most commonly used with nodejs
var server = express();
// Set public folder as root
server.use(express.static(path.join(__dirname, 'build')));

// tell the router to parse urlencoded data and JSON data for us
// and put it into req.query/req.body
server.use(bodyParser.urlencoded({
  extended: true
}));
server.use(bodyParser.json());

server.get('/ping', function (req, res) {
  return res.send('pong');
});
 
server.get('/', function (req, res) {
   res.sendFile(path.join(__dirname, 'build', 'index.html'));
});

let port = process.env.PORT || DEFAULT_PORT;
let host = process.env.IP || DEFAULT_HOST;
// set up the expressjs server and start it running
server.listen(port, host, function () {
  console.log('Server started at', host + ':' + port);
});

