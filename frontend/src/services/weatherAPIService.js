import axios from 'axios'
import weatherData from "./weather.json"

// CLEAR,
// CLOUDY,
// FOGGY,
// THUNDERY,
// RAINY,
// SLEETY,
// SNOWY,
// HAIL;

const getWeather = () => {
    return new Promise((resolve, reject) => {
        axios.get("http://localhost:8080/weatherapp/weather", {
        headers: {
            'Access-Control-Allow-Credentials':true
        },
        responseType: "json",
        })
        .then((res) => {
            resolve(res.data)})
        .catch(err => console.log(err))
    })
}

const getWardrobe = () => {

}

const postLocation = (data) => {
    //data = JSON.stringify(data);
    console.log(data);

    axios.post("http://localhost:8080/weatherapp/location", 
    data, {
        headers: {
            'Access-Control-Allow-Credentials':true
        },
        responseType: "json",
    })
    .then((res) => {console.log(res)})
    .catch(err => console.log(err))
}

export {getWeather, getWardrobe, postLocation}