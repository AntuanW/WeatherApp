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
    return weatherData;
    // axios.get("./weather.json")
    // .then((res) => {console.log(res)})
    // .catch(err => console.log(err))
}

const getWardrobe = () => {
    // axios.get("./weather.json")
    // .then((res) => {console.log(res)})
    // .catch(err => console.log(err))
}

const postLocation = (data) => {
    console.log(data);
    
}

export {getWeather, getWardrobe, postLocation}