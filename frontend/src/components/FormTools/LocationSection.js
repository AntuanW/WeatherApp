import React, { useState } from 'react';
import { Button } from '@mui/material';

import LocationInput from './LocationInput';

const LocationSection = () => {
    const [additionalLocation, setLocationAdded] = useState(false);
    
    const addLocation = () => {
        setLocationAdded(true);
    };

    return (
        <>
            <LocationInput
            title="First Location"
            latitudeLabel="latitude"
            longitudeLabel="longitude"/> 
            
            {!additionalLocation && <Button 
            variant="outlined" 
            onClick={addLocation} 
            sx={{ marginTop:2 }}>Add Location</Button>}
            
            {additionalLocation && <LocationInput
            title="Second Location"
            latitudeLabel="latitude2"
            longitudeLabel="longitude2"/> }
        </>
    );
};

export default LocationSection;