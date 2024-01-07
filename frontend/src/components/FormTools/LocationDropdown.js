import React, { useState, useEffect } from 'react';
import { Box, Typography, Button, IconButton } from '@mui/material'
import { getSavedLocationsFromStorage, removeLocationFromStorage } from '../../services/weatherAPIService'
import DeleteIcon from '@mui/icons-material/Delete';

const LocationDropdown = ({pasteSelectLocation}) => {
    const [savedLocations, setSavedLocations] = useState([]);

    useEffect(() => {
        const storedLocations = getSavedLocationsFromStorage();
        setSavedLocations(storedLocations)
      }, []);

      const handleSelectLocation = (location) => {
        pasteSelectLocation(location);
      };

    return (
        <>
<Box sx={{ border: 1, borderColor: 'grey.500', borderRadius: 1, p: 4, backgroundColor: 'whitesmoke', marginTop: 2}}>
<Typography variant='h8' sx={{ marginBottom: 2, color: '#0e0f3b', textAlign: 'center', textShadow: '1px 1px 1px #fff' }}>
         Saved Locations
       </Typography>
       <ul>
         {savedLocations.map((location, index) => (
           <li key={index}>
             <><b>{location.name}</b> (lat:{location.latitude}, long:{location.longitude}, time:{location.time})

             <IconButton aria-label="delete" size="large"
                onClick={() => {
                  setSavedLocations([...savedLocations.slice(0, index), ...savedLocations.slice(index + 1)])
                  removeLocationFromStorage(index)}}>
                <DeleteIcon fontSize="inherit" />
              </IconButton>

              <Button variant='outlined' onClick={() => handleSelectLocation(location)} sx={{ margin: 2}}>
               Use Location
             </Button>
             </>
           </li>
         ))}
       </ul>
     </Box>
        </>
    );
};

export default LocationDropdown;