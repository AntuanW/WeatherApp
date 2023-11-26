import React, { useEffect, useState } from 'react';
import {getWeather} from "../services/weatherAPIService.js";
import MasksOutlinedIcon from '@mui/icons-material/MasksOutlined';

const Weather = () => {
    const [weather, setWeather] = useState();


    useEffect(()=> {
        getWeather().then((res) => {console.log(res)})
        //setWeather);
        //console.log(weather);
    }, [])
    
    return (
        <div className='weather'>
            <MasksOutlinedIcon
                sx={{ fontSize: 100 }}/>
            {weather?.temperature}
            {weather?.airQuality}
            {weather?.forecast}

        </div>
    );
};

export default Weather;