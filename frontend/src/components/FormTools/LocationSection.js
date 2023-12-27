import React, { useState } from 'react';
import { Button } from '@mui/material';

import LocationInput from './LocationInput';

const LocationSection = () => {
    const [locationCount, setLocationCount] = useState(1);

    return (
        <>
            {new Array(locationCount).fill(0).map(function(object, i){
                return <LocationInput
                key = {i}
                title="Location"
                latitudeLabel={"latitude" + i}
                longitudeLabel={"longitude" + i}/> 
            })}
            
            {locationCount < 5 && <Button 
            variant="outlined" 
            onClick={() => setLocationCount(locationCount => locationCount + 1)} 
            sx={{ marginTop:2 }}>Add Location</Button>}

            {locationCount > 0 && <Button 
            variant="outlined" 
            onClick={() => setLocationCount(locationCount => locationCount -1)}  
            sx={{ marginTop:2 }}>Remove Location</Button>}
        </>
    );
};

export default LocationSection;