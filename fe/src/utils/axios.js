import axios from "axios";

const DOMAIN = process.env.REACT_APP_API_URL;
const PATH = process.env.REACT_APP_API_PATH;

console.log();
const baseURL = DOMAIN + PATH;
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
