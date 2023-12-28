import React from 'react';

import { Checkbox } from '@mui/material';
import BookmarkBorderIcon from '@mui/icons-material/BookmarkBorder';
import BookmarkIcon from '@mui/icons-material/Bookmark';

const SaveCheckBox = (props) => {
    const {id, setSaveLocation, saveLocation} = props

    const handleCheckboxChange = (event) => {
        setSaveLocation(event.target.checked);
    }

    return (
        <>
           <Checkbox
                id={id}
                checked={saveLocation}
                onChange={handleCheckboxChange}
                icon={<BookmarkBorderIcon />}
                checkedIcon={<BookmarkIcon />}
                variant="h10" 
                sx={{color: '#0e0f3b',  textAlign: 'center' }}
                inputProps={{ 'aria-label': 'save location checkbox' }}
            /> 
        </>
    );
};

export default SaveCheckBox;