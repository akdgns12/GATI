import axios from "axios";


console.log();

const httpRequest = axios.create({
  withCredentials: true,
  headers: {
    "Content-Type": `application/json`,
    "Access-Control-Allow-Credentials": true,
    "Access-Control-Allow-Origin": "*",
    "Access-Control-Allow-Methods": "GET,PUT,POST,DELETE,PATCH,OPTIONS",
  },
});

export default httpRequest;
