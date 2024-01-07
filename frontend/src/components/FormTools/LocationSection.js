import React, { useImperativeHandle, useState } from 'react';
import { Button, Typography } from '@mui/material';

import LocationInput from './LocationInput';

const LocationSection = React.forwardRef((props, ref) => {
    const [locationCount, setLocationCount] = useState(1);
    const [savedLocationsUse, setSavedLocationsUse] = useState(0);

    useImperativeHandle(ref, () => ({

        useSavedLocation(location) {
          console.log(location)
          setSavedLocationsUse(savedLocationsUse => savedLocationsUse + 1)
        }
    
      }));

    return (
        <>
            {new Array(locationCount).fill(0).map((object, i) => {
                return <LocationInput
                title={"Location " + i}
                latitudeLabel={"latitude" + i}
                longitudeLabel={"longitude" + i}
                timeLabel={"time" + i}
                nameLabel={"name" + i}
                key = {i}
                /> 
            })}

            {locationCount == 2 && <LocationInput
                title={"Location random"}
                latitudeLabel={"latitude random"}
                longitudeLabel={"longitude random"}
                timeLabel={"time random"}
                nameLabel={"name random"}
                /> }
            
            {locationCount < 5 && <Button 
            variant="outlined" 
            onClick={() => setLocationCount(locationCount => locationCount + 1)} 
            sx={{ marginTop:2 }}>Add Location</Button>}

            {locationCount > 1 && <Button 
            variant="outlined" 
            onClick={() => {
                setLocationCount(locationCount => locationCount -1);
            }}  
            sx={{ marginTop:2}}>Remove Location</Button>}

            <hr style={{marginTop:15}}/>
            <Typography variant="h6" sx={{ marginBottom: 3, marginTop: 3, color: '#0e0f3b', textAlign: 'center', textShadow: '1px 1px 1px #fff' }}> 
            Used saved locations</Typography>
        </>
    );
});

export default LocationSection;