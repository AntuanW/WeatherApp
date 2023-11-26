import React, { useState, useEffect } from 'react';
import { getWardrobe } from '../services/weatherAPIService.js';

const Wardrobe = () => {
    const [wardrobe, setWardrobe] = useState();

    useEffect(()=> {
        setWardrobe(getWardrobe());
    }, [])

    return (
        <div>
            aaaa
        </div>
    );
};

export default Wardrobe;