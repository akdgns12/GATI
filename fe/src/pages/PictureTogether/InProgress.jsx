import Typography from '@mui/material/Typography';
import Paper from '@mui/material/Paper';
import React from "react";
import MissionData from "./MissionData";
import Button from '@mui/material/Button';
import SetMemberModal from '../../components/PicsTg/SetMemberModal';

export default function InProgress() {
    const thisMission = MissionData[0]
    const [modal, setModal] = React.useState('false')
    const openModal = () => {
        setModal('true')
        console.log(modal)
    }

    return (
      <Paper
        style={{
          display:'flex',
          flexWrap:'wrap',
          width:'80vw',
          height:'40vh',
          flexDirection:'column',
          alignContent:'center',
        }}>
        <Typography style={{ fontWeight:'bold', margin:'20px' }}>{thisMission.title} </Typography>
        <Typography
          style={{ margin:'20px'}}>
          {thisMission.description}
        </Typography>
        <div>
          <Button onClick={openModal} size="medium" variant="contained"disableElevation>미션 참여하기</Button>
        </div>
        { modal === 'true' ? <SetMemberModal/> : null}
      </Paper>
    )
  }
