import React, { useState } from 'react';
import { Button } from '@mui/material';

import LocationInput from './LocationInput';

const LocationSection = () => {
    const [additionalLocation, setLocationAdded] = useState(1);
    
    const addLocation = () => {
        setLocationAdded(additionalLocation => additionalLocation + 1);
    };

    return (
        <>
            {new Array(additionalLocation).fill(0).map(function(object, i){
                return <LocationInput
                key = {i}
                title="Location"
                latitudeLabel={"latitude" + i}
                longitudeLabel={"longitude" + i}/> 
            })}
            
            {additionalLocation < 5 && <Button 
            variant="outlined" 
            onClick={addLocation} 
            sx={{ marginTop:2 }}>Add Location</Button>}
        </>
    );
};

export default LocationSection;