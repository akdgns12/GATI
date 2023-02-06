import axios from "axios";

const DOMAIN = "http://3.34.141.63";
const PORT = ":8080";
const baseURL = DOMAIN + PORT;
const httpClient = axios.create({
  baseURL,
  withCredentials: true,
  headers: {
    "Access-Control-Allow-Credentials": true,
    "Access-Control-Allow-Origin": "*",
    "Access-Control-Allow-Methods": "GET,PUT,POST,DELETE,PATCH,OPTIONS",
  },
});

export default httpClient;
