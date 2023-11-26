import React from 'react';
import Weather from './Weather';
import Wardrobe from './Wardrobe';
import "./styles/main.css"

const Main = () => {
    return (
        <main>
            <Weather/>
            <Wardrobe/>
        </main>
    );
};

export default Main;