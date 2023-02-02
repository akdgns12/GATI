import * as React from 'react';
import ToggleButton from '@mui/material/ToggleButton';
import ToggleButtonGroup from '@mui/material/ToggleButtonGroup';
import InProgress from '../../components/PicsTg/InProgress';
import UploadPics from '../../components/PicsTg/UploadPics';
import Completed from '../../components/PicsTg/Completed';

export default function PictureTogether() {
  const [mode, setMode] = React.useState('inprogress');
  const [mission, setMission] = React.useState(false)
  const [pics, setPics] = React.useState('')

  const changeMode = () => {
    if (mode === 'inprogress') {
      setMode('completed');
    } else {
      setMode('inprogress')
    }}

  const changeMissionMode = () => {
    setMission(true)
  }

  
  let content = null
  if (mode === 'inprogress' && mission === false) {
    content = <InProgress deliverPics={(_pic)=>setPics(_pic)} changeMissionMode={changeMissionMode} />
  } else if (mode === 'completed') {
    content = <Completed />
  } else if (mode === 'inprogress' && mission === true) {
    content = <UploadPics pics={pics} />
  }

  return (
    <div
      style={{
        display:'flex',
        flexDirection:'column',
        alignItems:'center'
      }}>
      <div>
        <ToggleButtonGroup
          color='primary'
          value={mode}
          exclusive
          onChange={changeMode}
          aria-label="Platform"
          style={{
            flex:'auto',
            margin:'30px',
            width:'80vw'
          }}
          >
          <ToggleButton value="inprogress" style={{ flex:1, height:'40px', backgroundColor:'white', border:'1px solid'}}>진행 중</ToggleButton><> </>
          <ToggleButton value="completed" style={{ flex:1, height:'40px', backgroundColor:'white', border:'1px solid'}}>완료</ToggleButton>
        </ToggleButtonGroup>
      </div>
      <div
        style={{
          display:'flex',
          flexDirection:'column',
          alignItems:'center',
          height:'70vh'
        }}>
        {content}
      </div>
    </div>
  );
}
