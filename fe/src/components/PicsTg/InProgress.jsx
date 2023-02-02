import Typography from '@mui/material/Typography';
import Paper from '@mui/material/Paper';
import React from "react";
import MissionData from "../../pages/PictureTogether/MissionData";
import Button from '@mui/material/Button';
import SetMemberModal from './SetMemberModal';
import DinnerImg from "../../static/PicsTgExampleDinner.png";

export default function InProgress(props) {

    const [pics, setPics] = React.useState('')
    const [selected, setSelected] = React.useState(false)
    const thisMission = MissionData[0]
    const [modal, setModal] = React.useState(false)
    const openModal = () => {
        setModal(true)
    }
    if (selected === true) {
      props.changeMissionMode()
    }
    if (pics != '') {
      props.deliverPics(pics)
    }
    return (
      <Paper
        style={{
          display:'flex',
          flexDirection:'column',
          flexWrap:'wrap',
          width:'80vw',
          flexDirection:'column',
          alignItems:'center',
        }}>
          <div
          style={{ flex:'auto'}}>
            <Typography style={{ fontWeight:'bold', margin:'20px' }}>{thisMission.title} </Typography>
          </div>
          <div>
            <Typography
              style={{ margin:'20px'}}>
              {thisMission.description}
            </Typography>
          </div>
          <div
            style={{
              display:'flex',
              flexDirection:'column',
              alignItems:'center',
            }}>
            <img
              src={DinnerImg}
              alt='exampleImg'
              width='200px'/>
          </div>
        <div
          style={{ margin:'20px'}}>
          <Button onClick={openModal} size="medium" variant="contained"disableElevation>미션 참여하기</Button>
        </div>
        { modal === true ? <SetMemberModal deliverPics={(_pic)=>{ setPics(_pic)}} onSelected={()=>{ setSelected(true)}} open={modal} onConfirm={(pics)=>{ return pics}} onClose={()=>{ setModal(false)}}/> : null}
      </Paper>
    )
  }
