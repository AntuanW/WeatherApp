import React from 'react';
import { useFormContext } from 'react-hook-form';
import { isValid, findInputErrors } from "../services/formCheck.js"
import { TextField } from '@mui/material';

const Input = (props) => {
    const {label, id, validation} = props;
    const {
        register,
        formState: { errors },
    } = useFormContext()

    const inputError = findInputErrors(errors, id);
    const isInvalid = !isValid(inputError);

    return (
        <TextField
            variant="standard"
            fullWidth
            style={{ margin: "10px 0" }}
            id={id}
            type="number"
            label={label}
            InputLabelProps={{ shrink: true }}
            inputProps={{ step: 0.001 }}
            helperText={inputError.error?.message}
            error = {isInvalid}
            {...register(id, validation)}
        />
    );
};

export default Input;