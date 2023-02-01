import * as React from 'react';
import ToggleButton from '@mui/material/ToggleButton';
import ToggleButtonGroup from '@mui/material/ToggleButtonGroup';
import InProgress from './InProgress';
import Completed from './Completed';

export default function PictureTogether() {
  const [mode, setMode] = React.useState('inprogress');

  const handleChange = (event, newMode) => {
    setMode(newMode);
  };
  
  let content = null
  if (mode === 'inprogress') {
    content = <InProgress />
  } else {
    content = <Completed />
  }

  return (
    <div
      style={{
        display:'flex',
        flexDirection:'column',
        alignContent:'center'
      }}>
      <div>
        <ToggleButtonGroup
          color="primary"
          value={mode}
          exclusive
          onChange={handleChange}
          aria-label="Platform"
          style={{
            flex:'auto',
            margin:'30px',
            width:'80vw'
          }}
          >
          <ToggleButton value="inprogress" style={{ flex:1, height:'40px'}}>진행 중</ToggleButton>
          <ToggleButton value="completed" style={{ flex:1, height:'40px'}}>완료</ToggleButton>
        </ToggleButtonGroup>
      </div>
      <div
        style={{
          display:'flex',
          justifyContent:'center',
        }}>
        {content}
      </div>
    </div>
  );
}