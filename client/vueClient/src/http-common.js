import axios from "axios";

export default axios.create({
    baseURL: "http://192.168.1.79:8080/api",
    headers: {
        "Content-type": "application/json"
    }
});