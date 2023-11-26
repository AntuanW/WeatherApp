import React from 'react';
import Weather from './Weather';
import Wardrobe from './Wardrobe';
import {  Button } from '@mui/material';
import { useNavigate } from "react-router-dom";


const Main = () => {
    const navigate = useNavigate();

    const onClick =() => {
        navigate("/", { replace: false });
    }


    return (
        <main>
            <Weather/>
            <Wardrobe/>
            <Button variant="outlined" onClick={onClick}>Change location</Button>
        </main>
    );
};

export default Main;