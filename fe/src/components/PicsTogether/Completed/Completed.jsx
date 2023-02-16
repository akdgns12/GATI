import * as React from 'react';
import ImageList from '@mui/material/ImageList';
import ImageListItem from '@mui/material/ImageListItem';
import ImageListItemBar from '@mui/material/ImageListItemBar';
import PicsTgExampleDinner from '../../../static/PicsTgExampleDinner.png'
import PicsTgExampleSky from '../../../static/PicsTgExampleSky.png'
import { Box, Typography } from '@mui/material';
/** @jsxImportSource @emotion/react */
import { css } from "@emotion/react";
import { useDispatch, useSelector } from 'react-redux';
import { asynGetMissionList } from '../../../store/PicsTogether/picsTg';
import { useNavigate } from 'react-router';
import MissionContribution from './MissionContribution';
import CombinedImg from '../Inprogress/OnMission/CombinedImg';


export default function Completed() {
  const navigate = useNavigate()
  const dispatch = useDispatch()
  const groupId = useSelector(state=>{return state.user.mainGroup}).id

  // MissionList 데이터 가져오기
  React.useEffect(()=>{
    dispatch(asynGetMissionList(groupId))
  },[])
  
  let getMissionList = useSelector(state=>{return state.picsTg.getMissionList})
  const completedMissions = getMissionList.filter(mission=>mission.completed == 2)
  const baseURL = process.env.REACT_APP_IMG_ROOT;

  return (
    <Box
      sx={{
        display:'flex',
        flexDirection:'column',
        flexWrap:'wrap',
      }}>
      <MissionContribution />
      {completedMissions ? 
        <ImageList cols={3} rowHeight={164}>
          {completedMissions.map((item) => (
              <ImageListItem
              style={{ overflow: 'hidden', display: 'flex', width: '100%', }}
              key={item.img}
            >
              <Box onClick={()=>navigate('/pictg/detail/'+ item.id)}>
                <img
                  style={{ objectFit: 'cover', width: '100%', height: '100%' }}
                  src={baseURL + '/' + item.img}
                  srcSet={baseURL + '/' + item.img}
                  alt={item.title}
                  loading="loading..."
                />
                <ImageListItemBar
                  sx={{ padding: '0px' }}
                  title=
                    {
                      <Typography variant="subtitle1" style={{ fontSize: 12 }}>
                        {'Mission:'+ item.adminMissionId}
                      </Typography>
                    }
                />
              </Box>
            </ImageListItem>
          ))}
        </ImageList>
      : <Typography>아직 완료한 미션이 없습니다.</Typography> }
    </Box>
  );
}
