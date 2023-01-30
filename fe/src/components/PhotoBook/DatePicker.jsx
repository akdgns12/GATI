import * as React from 'react';
import Paper from '@mui/material/Paper';
import TextField from '@mui/material/TextField';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { DatePicker } from '@mui/x-date-pickers/DatePicker';
import { width } from '@mui/system';
import { alignProperty } from '@mui/material/styles/cssUtils';

export default function BasicDatePicker() {
  const [value, setValue] = React.useState(null);

  return (
    <div
      style={{
        width:'30px',
      }}>
      <LocalizationProvider dateAdapter={AdapterDayjs}>
        <DatePicker
          style={{
            border: '0px'
          }}
          value={value}
          onChange={(newValue) => {
            setValue(newValue);
          }}
          renderInput={(params) => <TextField {...params} />}
          />
      </LocalizationProvider>
    </div>
  );
}