import * as React from 'react';
import IconButton from '@mui/material/IconButton';
import InputBase from '@mui/material/InputBase';
import SearchIcon from '@mui/icons-material/Search';

const InputSearchKeyword = () => {
  const [keyword, setKeyword] = React.useState("")

  // const onChange = (e) => {
  //   setKeyword(e.target.value)
  // }

  const onKeyDownHandler = (e) => {
    setKeyword(e.target.value)
    console.log('엔터 전:',keyword)
    if (e.key === 'Enter') {
      console.log('엔터 후:',keyword)
    }
  }

  return (
    <div
      component="form"
      style={{
        display:'flex',
        alignItems:'center',
        justifyContent:'center',
        margin: '10px',
        width:'500px',
        height: '40px',
        backgroundColor: '#E8E8E8',
        borderRadius: '8px',}}>
      <IconButton type="button" sx={{ marginLeft:'auto', p: '10px'}} aria-label="search">
        <SearchIcon />
      </IconButton>
      <InputBase
        component="form"
        defaultValue={keyword}
        // onChange={onChange}
        onKeyDown={onKeyDownHandler}
        sx={{
          width:'100%',
        }}
        placeholder="키워드로 사진을 검색하세요."
        inputProps={{ 'aria-label': 'search keywords'}}
        />
    </div>
  )
}
export default InputSearchKeyword