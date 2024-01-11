import React, {  useEffect } from 'react';
import { useFormContext } from 'react-hook-form';
import { TextField } from '@mui/material';


const TimeInput = (props) => {
    const {id, index} = props

    const { register, setValue } = useFormContext();

    useEffect(() => {
    const currentDate = new Date();
    const hours = currentDate.getHours().toString().padStart(2, '0');
    const minutes = currentDate.getMinutes().toString().padStart(2, '0');
    const defaultTime = `${hours}:${minutes}`;

    setValue(`location.${index}.${id}`, defaultTime); 
  }, [id, setValue]);


  return (
    <TextField
      label={id}
      id={`${id}${index}`}
      fullWidth
      style={{ margin: "10px 0" }}
      InputLabelProps={{ shrink: true }}
      type="time"
      onChange={(e) => setValue(e.target.value)}
      required={true}
      {...register(`location.${index}.${id}`)}
    />
  );
};

export default TimeInput;
