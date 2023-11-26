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
    axios.get("http://localhost:8080/weatherapp/weather", {
        headers: {
            'Access-Control-Allow-Credentials':true
        },
        responseType: "json",
    })
    .then((res) => {console.log(res)})
    .catch(err => console.log(err))
}

const getWardrobe = () => {
    axios.get("http://localhost:8080/weatherapp/wardrobe", {
        headers: {
            'Access-Control-Allow-Credentials':true
        },
        responseType: "json",
    })
    .then((res) => {console.log(res)})
    .catch(err => console.log(err))
}

const postLocation = (data) => {
    console.log(data);

    axios.post("http://localhost:8080/weatherapp/location", {
        body: data,
        headers: {
            'Access-Control-Allow-Credentials':true
        },
        responseType: "json",
    })
    .then((res) => {console.log(res)})
    .catch(err => console.log(err))
    
}

export { getWeather, getWardrobe, postLocation }