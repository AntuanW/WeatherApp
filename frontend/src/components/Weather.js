import React, { useEffect, useState } from 'react';
import {getWeather} from '../services/weatherAPIService.js';

const Weather = () => {
    const [weather, setWeather] = useState();

    useEffect(()=> {
        setWeather(getWeather());
    }, [])
    
    return (
        <div className='weather'>
            {weather?.temperature}
            {weather?.airQuality}
            {weather?.forecast}
        </div>
    );
};

export default Weather;